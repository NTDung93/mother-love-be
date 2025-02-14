package com.motherlove.models.payload.responseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiftResponse {
    private Long productId;
    private String productName;
    private String description;
    private int quantityOfGift;
    private String image;
}
