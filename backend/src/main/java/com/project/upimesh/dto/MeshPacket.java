package com.project.upimesh.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeshPacket {

    @NotBlank
    private String packetId;

    @Min(0)
    private int ttl;            // max hop remaining. intermediates decrements it

    @NotNull
    private Long createdAt;     // epoch millis

    @NotBlank
    private String ciphertext;  // base64 (RSA-encrypted AES key + AES-GCM ciphertext)

}
