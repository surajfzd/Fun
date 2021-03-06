package com.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.bean.Trainer;
import com.dao.FeedbackDAO;
import com.dao.IFeedbackDAO;
import com.exception.NoTrainerFoundException;
import com.service.FeedbackService;
import com.service.IFeedbackService;

public class View {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		IFeedbackDAO repo = new FeedbackDAO();
		IFeedbackService service = new FeedbackService(repo);

		while (true) 
		{
			System.out.println("Choose option");						//Creating menu
			System.out.println("1. Enter user details");
			System.out.println("2. Enter rating to view trainers");
			// System.out.println("3. exit");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {											
			case 1:														//To add feedback
				Trainer trainer = new Trainer();
				System.out.println("Enter trainer name");
				String name = sc.nextLine().trim();
				do{
					if(service.isValidName(name)) {
						break;
					}
					else {
						System.err.println("Enter trainer name");
						name = sc.nextLine().trim();
					}
				}while(true);
				trainer.setName(name);
				
				System.out.println("Enter course name");
				String course = sc.nextLine().trim();
				
				do{
					if(service.isValidCourse(course)) {
						break;
					}
					else {
						System.err.println("Enter Valid Course name");
						course = sc.nextLine().trim();
					}
				}while(true);
				trainer.setCourseName(course);
				
				System.out.println("Enter Start date: in format DD/MM/YYYY ");
				LocalDate date;
				while(true){
					try {
						String userInput = sc.nextLine();
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
						date = LocalDate.parse(userInput, dateFormat);
						break;
					}catch (Exception e) {
						System.err.println("Enter Valid Start date: in format DD/MM/YYYY ");
					}
				}
				trainer.setStartDate(date);

				System.out.println("Enter End date: in format DD/MM/YYYY  ");
				while(true){
					try {
						String userInput2 = sc.nextLine();
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
						date = LocalDate.parse(userInput2, dateFormat);
						break;
					}catch (Exception e) {
						System.err.println("Enter Valid End date: in format DD/MM/YYYY  ");
					}
				}
				trainer.setEndDate(date);

				System.out.println("Enter trainer rating");
				int rating = sc.nextInt();
				boolean b = true;
				while (b) {
					if (service.checkRating(rating)) {
						b = false;
					} else {
						System.err.println("Enter valid trainer rating");
						rating = sc.nextInt();
					}
				}
				trainer.setRating(rating);
				service.addFeedback(trainer);
				break;
				// trainer = new Trainer("A", "asa", LocalDate.of(2019, 1, 1),
				// LocalDate.of(2019, 1, 1), 2);
				// service.addFeedback(trainer);
				// Trainer trainer1 = new Trainer("B", "asa", LocalDate.of(2019, 1, 1),
				// LocalDate.of(2019, 1, 1), 2);
				// service.addFeedback(trainer1);
				// Trainer trainer2 = new Trainer("C", "asa", LocalDate.of(2019, 1, 1),
				// LocalDate.of(2019, 1, 1), 4);
				// service.addFeedback(trainer2);
				// Trainer trainer3 = new Trainer("D", "asa", LocalDate.of(2019, 1, 1),
				// LocalDate.of(2019, 1, 1), 2);
				// service.addFeedback(trainer3);
				// System.out.println(trainer);
				// System.out.println(trainer1);
				// System.out.println(trainer2);
				// System.out.println(trainer3);
				
			case 2:													//To check trainer on the basis of rating
				System.out.println("Enter rating");
				int rate = sc.nextInt();
				boolean c = true;
				while (c) {
					if (service.checkRating(rate)) {
						c = false;
					} else {
						System.err.println("Enter valid trainer rating");
						rate = sc.nextInt();
					}
				}
				try {
					HashMap<Integer, Trainer> hmap = service.getTrainerList(rate);
					for(Entry<Integer, Trainer> entry: hmap.entrySet()) {
						Trainer t = entry.getValue();
						System.out.println("Name:         "+t.getName()+"\n"
										 + "Course Name:  "+t.getCourseName()+"\n"
										 + "Start Date:   "+t.getStartDate());
					}
					break;
				} catch (NoTrainerFoundException e) {
					System.err.println("No Trainer Found With This Rating");
				}
				break;

			default:
				System.err.println("You entered wrong choice");

			}
		}

	}

}

// System.out.println("Enter Start date: ");
// System.out.println(" starting day: ");
// int d1=sc.nextInt();
// System.out.println(" starting month: ");
// int m1=sc.nextInt();
// System.out.println(" starting year: ");
// int y1=sc.nextInt();
//
// System.out.println("Enter end date: ");
// System.out.println(" starting day: ");
// int d2=sc.nextInt();
// System.out.println(" starting month: ");
// int m2=sc.nextInt();
// System.out.println(" starting year: ");
// int y2=sc.nextInt();
//
// LocalDate startDate=LocalDate.of(d1, m1, y1);
// trainer.setStartDate(startDate);
// LocalDate endDate=LocalDate.of(d2, m2, y2);
// trainer.setEndDate(endDate);
