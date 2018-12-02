import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DataCollector {

	ArrayList<RunData> runs[] = new ArrayList[8];
	
	public DataCollector(int r) {
		runs[0] = new ArrayList<RunData>();
		runs[1] = new ArrayList<RunData>();
		runs[2] = new ArrayList<RunData>();
		runs[3] = new ArrayList<RunData>();
		runs[4] = new ArrayList<RunData>();
		runs[5] = new ArrayList<RunData>();
		runs[6] = new ArrayList<RunData>();
		runs[7] = new ArrayList<RunData>();
	}
	
	public void addRun(RunData run, int spinnerVal) {
		runs[spinnerVal].add(run);
	}
	
	public void outputData() throws IOException {
		File outputFile = new File("C:/Users/Kush/Desktop/Probability Simulations/ProbabiltiySimulations/Culminating/Output Data/Output.txt");
		
		if(outputFile.createNewFile()){
		    System.out.println("File Created");
		} else { System.out.println("File already exists"); }
		
		FileOutputStream fos = new FileOutputStream(outputFile);
		
		List<String> lines = new ArrayList<String>();
		
		for(int spinner = 0; spinner < 8; spinner++) {
			String sV = "Spinner Value = " + spinner; 
			
			//FBS
			for(int i = 0; i < 26; i++) {
				String line = sV + ". FBS " + i + " = " + getNumFBS(i, spinner) + ".";
				lines.add(line);
			}
			
			//House Wins
			String hline = sV + ". House Wins = " + getHouseWins(spinner);
			lines.add(hline);
			
			//Spinner Values
			String spinLine = sV + ". Total times landed = " + getNumSpinnerVals(spinner);
			lines.add(spinLine);
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for (int i = 0; i < lines.size(); i++) {
			bw.write(lines.get(i));
			bw.newLine();
		}
		
		int totalRuns = runs[0].size();
		totalRuns += runs[1].size();
		totalRuns += runs[2].size();
		totalRuns += runs[3].size();
		totalRuns += runs[4].size();
		totalRuns += runs[5].size();
		totalRuns += runs[6].size();
		totalRuns += runs[7].size();
		
		bw.write("Total Runs: " + totalRuns);
		
		bw.close();
	}
	
	public int getNumSpinnerVals(int spinner) {
		return runs[spinner].size();
	}
	
	public int getHouseWins(int spinner) {
		int hWins = 0;
		for(int i = 0; i < runs[spinner].size(); i++) {
			if(runs[spinner].get(i).getHouseWin()) {
				hWins++;
			}
		}
		return hWins;
	}
	
	public int getNumFBS(int fbs, int spinner) {
		int numFBS = 0;
		for(int i = 0; i < runs[spinner].size(); i++) {
			if(runs[spinner].get(i).getFailsBeforeSuccess() == fbs) {
				numFBS++;
			}
		}
		
		return numFBS;
	}
}
