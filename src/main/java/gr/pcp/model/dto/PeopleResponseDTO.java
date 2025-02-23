package gr.pcp.model.dto;

import gr.pcp.model.PersonModel;

import java.util.List;

public class PeopleResponseDTO {

    private final List<PersonModel> data;
    private final long total;

    public PeopleResponseDTO(List<PersonModel> data) {
        this.data = data;
        this.total = data.size();
    }

    public List<PersonModel> getData() {
        return data;
    }

    public long getTotal() {
        return total;
    }

}
