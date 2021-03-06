package com.dao;

import java.util.HashMap;

import com.bean.Trainer;
import com.exception.NoTrainerFoundException;

public interface IFeedbackDAO 
{
	public Trainer addFeedback(Trainer trainer);
	public HashMap<Integer,Trainer> getTrainerList(int rate) throws NoTrainerFoundException;

}
