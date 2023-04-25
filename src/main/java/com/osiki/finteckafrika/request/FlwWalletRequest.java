package com.osiki.finteckafrika.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlwWalletRequest {

    private String email;
    private String firstname;
    private String lastname;
    private String bvn;
    private String tx_ref;
    private String phoneNumber;
    private String narration;
    @JsonProperty(value = "is_Permanent")
    private boolean is_permanent;
}
