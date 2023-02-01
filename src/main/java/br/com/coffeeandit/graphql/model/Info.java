package br.com.coffeeandit.graphql.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Info {

    private String bibKey;
    private String id;
    private String infoUrl;
    private String preview;
    private String previewUrl;
    private String thumbnailUrl;
}
