package bohun.flower.app.controller;

import bohun.flower.app.dto.discount.CreateDiscountRequestDto;
import bohun.flower.app.dto.discount.DiscountDto;
import bohun.flower.app.service.Discount.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Discount management", description = "Endpoints for managing discounts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping
    @Operation(description = "Get all discounts")
    @ApiResponse(responseCode = "200", description = "All types")
    public List<DiscountDto> getAll(Pageable pageable) {
        return discountService.findAll(pageable);
    }

    @GetMapping("/{discountId}")
    @Operation(description = "get discount by id")
    @ApiResponse(responseCode = "200", description = "type by Id")
    public DiscountDto getDiscountById(@PathVariable Long discountId) {
        return discountService.getDiscountById(discountId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(description = "Create new type")
    @ApiResponse(responseCode = "201", description = "Create new type")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountDto createType(@RequestBody @Valid CreateDiscountRequestDto createDiscountRequestDto) {
        return discountService.save(createDiscountRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{discountId}")
    @Operation(description = "Update type")
    @ApiResponse(responseCode = "200", description = "Update type")
    public DiscountDto updateDiscount(@PathVariable Long discountId,
                                      @RequestBody CreateDiscountRequestDto createDiscountRequestDto) {
        return discountService.updateDiscount(discountId, createDiscountRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{discountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete discount by Id")
    @ApiResponse(responseCode = "200", description = "Delete discount")
    public void deleteDiscount(@PathVariable Long discountId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().startsWith("admin")) discountService.deleteDiscount(discountId);
    }
}
