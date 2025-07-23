package tien.example.demo2.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemBasicDTO {
    private Long id;
    private String name;
}
