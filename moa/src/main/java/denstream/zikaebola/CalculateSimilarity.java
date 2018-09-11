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


public class CalculateSimilarity 
{

    public static double ComputeVSM(double[][] matrix_data1, double[][] matrix_data2, int count)
    {
        double similarity = 0.0;

        int vec_length = count * count;
        double[] vec1 = new double[vec_length];
        double[] vec2 = new double[vec_length];
        
        for (int i = 0; i < count; i++)
        {
            for (int j = 0; j < count; j++)
            {
                vec1[i*count+j] = matrix_data1[i][j];
                vec2[i*count+j] = matrix_data2[i][j];
            }
        }

        double up = 0.0;
        double down1 = 0.0;
        double down2 = 0.0;
        for (int i = 0; i < vec_length; i++)
        {
            up += vec1[i] * vec2[i];
            down1 += vec1[i] * vec1[i];
            down2 += vec2[i] * vec2[i];
        }

        similarity = up / (Math.sqrt(down1) * Math.sqrt(down2));

        return similarity;
    }

	public static double calculateSimilarity(String inputfileName_Matrix1,
			String inputfileName_Matrix2,
			int overall_max_cluster_id)
	{

        double[][] matrix_data1 = new double[overall_max_cluster_id][overall_max_cluster_id];
        double[][] matrix_data2 = new double[overall_max_cluster_id][overall_max_cluster_id];
        for (int iRow = 0; iRow < overall_max_cluster_id; iRow++)
        {
            for (int iCol = 0; iCol < overall_max_cluster_id; iCol++)
            {
            	matrix_data1[iRow][iCol] = 0;
            	matrix_data2[iRow][iCol] = 0;
            }
        }

		/////////////////////////////////////////////////////////////////////////////
		//
		//
		//
		/////////////////////////////////////////////////////////////////////////////
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			String str = "";
			fis = new FileInputStream(inputfileName_Matrix1);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			str = br.readLine();
			int all_link_count = Integer.parseInt(str.split(",")[0]);
			int iRow = 0;
			while ((str = br.readLine()) != null) 
			{
				String[] temp_line_array = str.split(",");
				int len = temp_line_array.length;
				for (int iCol=1; iCol<len; iCol++)
				{
					matrix_data1[iRow][iCol-1] = Double.parseDouble(temp_line_array[iCol]) / all_link_count;
				}
				iRow++;
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
		
		/////////////////////////////////////////////////////////////////////////////
		//
		//
		//
		/////////////////////////////////////////////////////////////////////////////
		try {
			String str = "";
			fis = new FileInputStream(inputfileName_Matrix2);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			str = br.readLine();
			int all_link_count = Integer.parseInt(str.split(",")[0]);
			int iRow = 0;
			while ((str = br.readLine()) != null) 
			{
				String[] temp_line_array = str.split(",");
				int len = temp_line_array.length;
				for (int iCol=1; iCol<len; iCol++)
				{
					matrix_data2[iRow][iCol-1] = Double.parseDouble(temp_line_array[iCol]) / all_link_count;
				}
				iRow++;
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
		
		/////////////////////////////////////////////////////////////////////////////
		//
		//
		//
		/////////////////////////////////////////////////////////////////////////////
		return ComputeVSM(matrix_data1, matrix_data2, overall_max_cluster_id);
	}
}
