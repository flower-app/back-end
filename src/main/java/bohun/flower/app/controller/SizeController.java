package bohun.flower.app.controller;

import bohun.flower.app.dto.size.CreateSizeRequestDto;
import bohun.flower.app.dto.size.SizeDto;
import bohun.flower.app.service.size.SizeService;
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

@Tag(name = "Size management", description = "Endpoints for managing sizes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/sizes")
public class SizeController {
    private final SizeService sizeService;

    @GetMapping
    @Operation(description = "Get all sizes")
    @ApiResponse(responseCode = "200", description = "All sizes")
    public List<SizeDto> getAll(Pageable pageable) {
        return sizeService.findAll(pageable);
    }

    @GetMapping("/{sizeId}")
    @Operation(description = "get size by id")
    @ApiResponse(responseCode = "200", description = "size by Id")
    public SizeDto getSizeById(@PathVariable Long sizeId) {
        return sizeService.getSizeById(sizeId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(description = "Create new size")
    @ApiResponse(responseCode = "201", description = "Create new size")
    @ResponseStatus(HttpStatus.CREATED)
    public SizeDto createSize(@RequestBody @Valid CreateSizeRequestDto createSizeRequestDto) {
        return sizeService.save(createSizeRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{sizeId}")
    @Operation(description = "Update size")
    @ApiResponse(responseCode = "200", description = "Update size")
    public SizeDto updateSize(@PathVariable Long sizeId,
                                      @RequestBody CreateSizeRequestDto createSizeRequestDto) {
        return sizeService.updateSize(sizeId, createSizeRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{sizeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete size by Id")
    @ApiResponse(responseCode = "200", description = "Delete size")
    public void deleteSize(@PathVariable Long sizeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().startsWith("admin")) sizeService.deleteSize(sizeId);
    }
}
