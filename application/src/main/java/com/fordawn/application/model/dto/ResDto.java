package com.fordawn.application.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResDto {

    private List<String > stringList;

    private List<Long> longList;
}
