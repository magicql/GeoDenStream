package denstream.zikaebola;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import denstream.configure.StatisticIntervalData;

public class LoadDailyPoints 
{
	public static void loadPointsWithinADay(String fileName,
			int date_count,
			Date[] start_date_list,
			Date[] end_date_list,
			int current_day_idx, 
			ArrayList<double[]> coord_list,
			ArrayList<Date> time_list,
			ArrayList<String> outBuffer)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			String str = "";
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			str = br.readLine();
			Date temp_date = null;
			String tw_x_str;
			String tw_y_str;
			double temp_x_val;
			double temp_y_val;
			while ((str = br.readLine()) != null) 
			{
				String[] temp_line_array = str.split(",");
				String time_str = temp_line_array[0];
				try {
					temp_date = sdf.parse(time_str);
				} catch (ParseException e) {
					e.printStackTrace();
				}		
				int date_idx = StatisticDailyData.getDateIndex(temp_date, start_date_list, end_date_list);
				
				////////////////////////////////////////////////////////////////
				if (date_idx == -1)
				{
					continue;
				}
				if (date_idx == -2)
				{
					break;
				}
				
				////////////////////////////////////////////////////////////////
				if (date_idx < current_day_idx)
				{
					continue;				
				}
				if (date_idx > current_day_idx)
				{
					break;
				}

				////////////////////////////////////////////////////////////////
				if (date_idx == current_day_idx)
				{
					outBuffer.add(str);
					tw_x_str = temp_line_array[8];
					tw_y_str = temp_line_array[9];
					temp_x_val = Double.parseDouble(tw_x_str);
					temp_y_val = Double.parseDouble(tw_y_str);

					double[] coord = new double[2];
					coord[0] = temp_x_val;
					coord[1] = temp_y_val;
					coord_list.add(coord);
					time_list.add(temp_date);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("cannot find file");
		} catch (IOException e) {
			System.out.println("read file failed");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////
	//
	//
	//
	/////////////////////////////////////////////////////////////////////////////////
	public static void loadPointsWithinAnInterval_WithBuffer(String fileName,
			int interval_count,
			Date[] start_date_list,
			Date[] end_date_list,
			int current_interval_idx,
			ArrayList<double[]> coord_list,
			ArrayList<double[]> connected_coord_list,
			ArrayList<Date> time_list,
			ArrayList<String> record_buffer)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			String str = "";
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			str = br.readLine();
			Date temp_date = null;
			String tw_x_str;
			String tw_y_str;
			double temp_x_val;
			double temp_y_val;
			while ((str = br.readLine()) != null) 
			{
				String[] temp_line_array = str.split(",");
				String time_str = temp_line_array[0];
				try {
					temp_date = sdf.parse(time_str);
				} catch (ParseException e) {
					e.printStackTrace();
				}		
				int interval_idx = StatisticIntervalData.getTimeIntervalIndex(temp_date, 
						start_date_list, end_date_list);
				
				////////////////////////////////////////////////////////////////
				if (interval_idx == -1)
				{
					continue;
				}
				if (interval_idx == -2)
				{
					break;
				}
				
				////////////////////////////////////////////////////////////////
				if (interval_idx < current_interval_idx)
				{
					continue;				
				}
				if (interval_idx > current_interval_idx)
				{
					break;
				}

				////////////////////////////////////////////////////////////////
				if (interval_idx == current_interval_idx)
				{
					tw_x_str = temp_line_array[8];
					tw_y_str = temp_line_array[9];
					temp_x_val = Double.parseDouble(tw_x_str);
					temp_y_val = Double.parseDouble(tw_y_str);

					double[] coord = new double[2];
					coord[0] = temp_x_val;
					coord[1] = temp_y_val;
					coord_list.add(coord);
					time_list.add(temp_date);

					//if (connected_x_index>=0 && connected_y_index>=0)
					{
						tw_x_str = temp_line_array[11];
						tw_y_str = temp_line_array[12];
						temp_x_val = Double.parseDouble(tw_x_str);
						temp_y_val = Double.parseDouble(tw_y_str);
						double[] coord1 = new double[2];
						coord1[0] = temp_x_val;
						coord1[1] = temp_y_val;
						connected_coord_list.add(coord1);
					}
					record_buffer.add(str);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("cannot find file");
		} catch (IOException e) {
			System.out.println("read file failed");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
