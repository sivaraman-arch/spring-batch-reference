package com.sivaraman.reference.service.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class FirstItemWriter implements ItemWriter<Long> {

  @Override
  public void write(List<? extends Long> list) throws Exception {
    log.info("Inside item writer , received : {}", list);
    list.forEach(System.out::println);
  }
}
