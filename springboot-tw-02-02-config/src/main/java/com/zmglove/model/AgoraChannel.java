package com.zmglove.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author CZH
 * @version 1.0
 * @date 2019/7/17 9:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgoraChannel {
    private Integer id;

    private Integer lessonPlanId;

    private Integer teacherId;

    private Integer studentId;

    private String token;

    private String name;
}
