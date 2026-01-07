package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuestModel(
        String id,
        String title,
        String previewImg,
        String previewImgWebp,
        String coverImg,
        String coverImgWebp,
        String level,
        String type,
        List<Integer> peopleMinMax
) {}