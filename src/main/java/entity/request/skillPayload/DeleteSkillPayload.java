package entity.request.skillPayload;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
@Getter
@Setter
public class DeleteSkillPayload {
    public ArrayList<String> data;
}
