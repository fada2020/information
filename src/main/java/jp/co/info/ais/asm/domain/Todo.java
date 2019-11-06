package jp.co.info.ais.asm.domain;

import lombok.*;

@Getter @Setter
@ToString(exclude = "finished")
public class Todo {
    private int id;
    private String title;
    private String details;
    private boolean finished;
}
