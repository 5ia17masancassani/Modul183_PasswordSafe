package com.example.PasswordSafe;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    private List<Record> records = new ArrayList<>();

    public void addRecord(Record record) {
        records.add(record);
    }

    public void removeRecord(int id) {
        Record record = null;
        for (Record r: this.getRecords()){
            if(r.getId() == id){
                record = r;
            }
        }
        if(record != null){
            records.remove(record);
        }

    }

    public List<Record> getRecords() {
        return records;
    }
}
