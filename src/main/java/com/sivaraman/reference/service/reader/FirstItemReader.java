package com.sivaraman.reference.service.reader;


import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class FirstItemReader implements ItemReader<Integer> {

    List<Integer> records = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
    int itr = 0;

    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        log.info("Inside item reader");
        Integer item;

        if(itr < this.records.size() - 1){
            itr +=1;
            item = records.get(itr);
            return item;
        }
        return null;
    }
}
