package br.edu.ifrs.riogrande.tads.tds.util.controller.dto;

import java.util.List;

public class UuidResponseDTO {
    private final List<String> uuids;
    private final int count;

    public UuidResponseDTO(List<String> uuids) {
        this.uuids = uuids;
        this.count = uuids.size();
    }

    public List<String> getUuids() {
        return uuids;
    }

    public int getCount() {
        return count;
    }
} 