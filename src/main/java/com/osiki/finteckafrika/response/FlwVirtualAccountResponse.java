package com.osiki.finteckafrika.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlwVirtualAccountResponse {

    private String status;
    private String message;
    private Data data;



    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {

        @JsonProperty(value = "response_code")
        private String responseCode;
        @JsonProperty(value = "response_message")
        private String responseMessage;
        @JsonProperty(value = "flw_ref")
        private String flwRef;
        @JsonProperty(value = "order_ref")
        private String order_ref;
        @JsonProperty(value = "account_number")
        private String accountNumber;
        @JsonProperty(value = "bank_name")
        private String bankName;
        @JsonProperty(value = "note")
        private String note;
        @JsonProperty(value = "frequency")
        private String frequency;
        @JsonProperty(value = "created_at")
        private String createdAt;
        @JsonProperty(value = "amount")
        private String amount;


    }
}
