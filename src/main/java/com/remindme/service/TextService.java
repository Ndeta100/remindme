package com.remindme.service;

import com.remindme.entity.Text;

import java.util.List;

public interface TextService {

    public void addText(Long occasionId, Text text);
    public List<Text>getTexts(Long occasionId);
    public List<Text>getTextsByUser(Long accountId);

}
