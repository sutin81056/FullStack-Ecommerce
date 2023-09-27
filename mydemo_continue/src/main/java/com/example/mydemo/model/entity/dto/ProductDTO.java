package com.example.mydemo.model.entity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDTO {
    Long id;
    String name;
    Integer price;
    String image;

}
