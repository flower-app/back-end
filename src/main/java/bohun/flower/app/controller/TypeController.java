package bohun.flower.app.controller;

import bohun.flower.app.dto.type.CreateTypeRequestDto;
import bohun.flower.app.dto.type.TypeDto;
import bohun.flower.app.service.type.TypeService;
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

@Tag(name = "Type management", description = "Endpoints for managing types")
@RequiredArgsConstructor
@RestController
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    @GetMapping
    @Operation(description = "Get all types")
    @ApiResponse(responseCode = "200", description = "All types")
    public List<TypeDto> getAll(Pageable pageable) {
        return typeService.findAll(pageable);
    }

    @GetMapping("/{typeId}")
    @Operation(description = "get type by id")
    @ApiResponse(responseCode = "200", description = "type by Id")
    public TypeDto getTypeById(@PathVariable Long typeId) {
        return typeService.getTypeById(typeId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(description = "Create new type")
    @ApiResponse(responseCode = "201", description = "Create new type")
    @ResponseStatus(HttpStatus.CREATED)
    public TypeDto createType(@RequestBody @Valid CreateTypeRequestDto createTypeRequestDto) {
        return typeService.save(createTypeRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{typeId}")
    @Operation(description = "Update type")
    @ApiResponse(responseCode = "200", description = "Update type")
    public TypeDto updateType(@PathVariable Long typeId,
                                      @RequestBody CreateTypeRequestDto createTypeRequestDto) {
        return typeService.updateType(typeId, createTypeRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{typeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete type by Id")
    @ApiResponse(responseCode = "200", description = "Delete type")
    public void deleteType(@PathVariable Long typeId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().startsWith("admin")) typeService.deleteType(typeId);
    }
}
