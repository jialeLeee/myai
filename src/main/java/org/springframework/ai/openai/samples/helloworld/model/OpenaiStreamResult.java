package org.springframework.ai.openai.samples.helloworld.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: movie
 * @date: 2024/7/9 16:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenaiStreamResult {
    private String result;
}
