package com.osiki.finteckafrika.response;

import com.osiki.finteckafrika.entity.FlwBank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlwGetAllBankResponse {
    private String status;
    private String message;
    private List<FlwBank> data = new ArrayList<>();

}
