package com.skynest.models;

import com.skynest.utils.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditUserRequest {
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String positionInCompany;

    public static EditUserRequest generateValidEditRequest() {
        return EditUserRequest.builder()
                .name(RandomGenerator.generateRandomFirstName())
                .surname(RandomGenerator.generateRandomLastName())
                .phoneNumber(RandomGenerator.generateRandomPhoneNumber())
                .address(RandomGenerator.generateRandomAddress())
                .positionInCompany(RandomGenerator.generateRandomPositionInCompany())
                .build();
    }
}
