package com.spo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.Math;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Workforce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Enter contract details");
			Scanner sc = new Scanner(System.in);
			String stringToParse = sc.nextLine();

			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(stringToParse);

			JSONArray rooms = (JSONArray) json.get("rooms");
			Object[] objRooms = rooms.toArray();
			int[] intRooms = new int[objRooms.length];
			int[] backupdata = new int[objRooms.length];
			for (int i = 0; i < objRooms.length; i++) {
				intRooms[i] = ((Long) objRooms[i]).intValue();
				backupdata[i] = ((Long) objRooms[i]).intValue();
			}
			String senior = String.valueOf(json.get("senior"));
			String junior = String.valueOf(json.get("junior"));

			int seniors = Integer.parseInt(senior);
			int juniors = Integer.parseInt(junior);
			Contractor con = new Contractor(intRooms, seniors, juniors);
			
			double limit = calculateWorkforceLimit(con);			
			int[][] output = optimizeWorkforce(con, limit);
			// creating JSONObject 
			StringBuilder str = new StringBuilder(); 
			str.append("[");
			for(int i=0;i<output.length;i++) {
	        	int idx = getIndexInSortedArray(backupdata,backupdata.length,i);
	        	//System.out.println("senior: "+output[idx][0]+" junior:"+output[idx][1]);
	        	str.append("{senior: "+ output[idx][0]+",");
	        	str.append("junior: "+ output[idx][1]+"}");
	        	if(i<(output.length-1))
	        		str.append(",");
	        	
			}
			str.append("]");
			System.out.println(str.toString());
			

		} catch (ParseException ne) {
			System.out.println("Invalid format");
			;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static double calculateWorkforceLimit(Contractor con) {
		int[] rooms = con.getRooms();
		int max = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] > max)
				max = rooms[i];
		}
		System.out.println("Max value is " + max);
		double limit = max / 10.0;

		if (Math.ceil(limit) > limit) {
			limit = Math.ceil(limit);
			System.out.println(Math.ceil(limit));
		} else
			System.out.println(limit);
		return limit;
	}

	public static int[][] optimizeWorkforce(Contractor con, double limit) {

		int[] rooms = con.getRooms();
		int seniors = con.getSeniors();
		int juniors = con.getJuniors();
		int swr = seniors;
		int jwr = juniors;
		int output[][] = new int[rooms.length][2];
		List<Integer> sen_worker = new ArrayList<Integer>();
		List<Integer> jun_worker = new ArrayList<Integer>();
		int cnt = 0;
		while (cnt < rooms.length) {
			sen_worker.add(1);
			jun_worker.add(0);
			cnt++;
			// seniors=seniors - 4;
		}

		Arrays.sort(rooms);
		int i = rooms.length - 1;

		while (i >= 0) {
			System.out.println("Iteration " + (i) + ": " + rooms[i]);
			int optimized = RequiredEffortsForStructure(sen_worker.get(i), jun_worker.get(i), swr, jwr);

			while (optimized < rooms[i]) {
				int temp = RequiredEffortsForStructure(sen_worker.get(i), jun_worker.get(i), swr, jwr);
				if (juniors > 0) {
					while (temp < rooms[i]) {
						int num = jun_worker.get(i);
						num++;
						jun_worker.set(i, num);
						System.out.println(" " + sen_worker.get(i) + " + " + jun_worker.get(i));
						temp = RequiredEffortsForStructure(sen_worker.get(i), jun_worker.get(i), swr, jwr);
					}
				} else if (juniors == 0 && seniors > 0) {
					int num = sen_worker.get(i);
					num++;
					sen_worker.set(i, num);
					jun_worker.set(i, 0);
					optimized = RequiredEffortsForStructure(sen_worker.get(i), jun_worker.get(i), swr, jwr);
				}

				int total_workers = sen_worker.get(i) + jun_worker.get(i);
				if (total_workers > (int) limit) {
					int num = sen_worker.get(i);
					num++;
					sen_worker.set(i, num);
					jun_worker.set(i, 0);
					optimized = RequiredEffortsForStructure(sen_worker.get(i), jun_worker.get(i), swr, jwr);

				} else {
					optimized = RequiredEffortsForStructure(sen_worker.get(i), jun_worker.get(i), swr, jwr);
				}
			}
			output[i][0] = sen_worker.get(i);
			output[i][1] = jun_worker.get(i);
			System.out.println("Senior " + output[i][0] + " Junior " + output[i][1]);
			seniors = seniors - sen_worker.get(i);
			juniors = juniors - jun_worker.get(i);
			// System.out.println("Available Senior "+seniors+" Available Junior "+juniors);
			i--;
		}
		return output;

	}

	public static int RequiredEffortsForStructure(int sen_worker, int jun_worker, int swr, int jwr) {
		int optimized = sen_worker * swr + jun_worker * jwr;
		return optimized;
	}
	
	/*
	 * Count of elements smaller than current element plus the equal element
	 * occurring before given index
	 */
	public static int getIndexInSortedArray(int arr[], int n, int idx) {
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] < arr[idx])
				result++;
			if (arr[i] == arr[idx] && i < idx)
				result++;
		}
		return result;
	}


}
