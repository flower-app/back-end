package bohun.flower.app.controller;

import bohun.flower.app.dto.contains.ContainsDto;
import bohun.flower.app.dto.contains.CreateContainsRequestDto;
import bohun.flower.app.dto.product.ProductDto;
import bohun.flower.app.service.contains.ContainsService;
import bohun.flower.app.service.product.ProductService;
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

@Tag(name = "Contain management", description = "Endpoints for managing contains")
@RequiredArgsConstructor
@RestController
@RequestMapping("/contains")
public class ContainsController {
    private final ContainsService containsService;

    @GetMapping
    @Operation(description = "Get all contains")
    @ApiResponse(responseCode = "200", description = "All contains")
    public List<ContainsDto> getAll(Pageable pageable) {
        return containsService.findAll(pageable);
    }

    @GetMapping("/{containsId}")
    @Operation(description = "get contains by id")
    @ApiResponse(responseCode = "200", description = "contains by Id")
    public ContainsDto getContainsById(@PathVariable Long containsId) {
        return containsService.getContainsById(containsId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(description = "Create new contains")
    @ApiResponse(responseCode = "201", description = "Create new contains")
    @ResponseStatus(HttpStatus.CREATED)
    public ContainsDto createContains(@RequestBody @Valid CreateContainsRequestDto createContainsRequestDto) {
        return containsService.save(createContainsRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{containsId}")
    @Operation(description = "Update contains")
    @ApiResponse(responseCode = "200", description = "Update contains")
    public ContainsDto updateContains(@PathVariable Long containsId,
                                @RequestBody CreateContainsRequestDto createContainsRequestDto) {
        return containsService.updateContains(containsId, createContainsRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{containsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete contains by Id")
    @ApiResponse(responseCode = "200", description = "Delete contains")
    public void deleteContains(@PathVariable Long containsId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().startsWith("admin")) containsService.deleteContains(containsId);
    }
}
