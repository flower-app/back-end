package bohun.flower.app.controller;

import bohun.flower.app.dto.color.ColorDto;
import bohun.flower.app.dto.color.CreateColorRequestDto;
import bohun.flower.app.service.color.ColorService;
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

@Tag(name = "Color management", description = "Endpoints for managing colors")
@RequiredArgsConstructor
@RestController
@RequestMapping("/colors")
public class ColorController {
    private final ColorService colorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(description = "Create new color")
    @ApiResponse(responseCode = "201", description = "Create new color")
    @ResponseStatus(HttpStatus.CREATED)
    public ColorDto createColor(@RequestBody @Valid CreateColorRequestDto createColorRequestDto) {
        return colorService.save(createColorRequestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{colorId}")
    @Operation(description = "Update color")
    @ApiResponse(responseCode = "200", description = "Update color")
    public ColorDto updateColor(@PathVariable Long colorId,
                                @RequestBody CreateColorRequestDto createColorRequestDto) {
        return colorService.updateColor(colorId, createColorRequestDto);
    }

    @GetMapping
    @Operation(description = "Get all colors")
    @ApiResponse(responseCode = "200", description = "All colors")
    public List<ColorDto> getAll(Pageable pageable) {
        return colorService.findAll(pageable);
    }

    @GetMapping("/{colorId}")
    @Operation(description = "get color by id")
    @ApiResponse(responseCode = "200", description = "color by Id")
    public ColorDto getColorById(@PathVariable Long colorId) {
        return colorService.getColorById(colorId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{colorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete color by Id")
    @ApiResponse(responseCode = "200", description = "Delete color")
    public void deleteColor(@PathVariable Long colorId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().startsWith("admin")) colorService.deleteColor(colorId);
    }
}
