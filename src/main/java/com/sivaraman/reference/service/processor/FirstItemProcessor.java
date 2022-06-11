package com.sivaraman.reference.service.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FirstItemProcessor implements ItemProcessor<Integer, Long> {
    @Override
    public Long process(Integer integer) throws Exception {
       log.info("Inside processor : {}", integer);
       return integer + 20L;
    }
}
