package application;

import java.util.Comparator;

public class ProjectComparator implements Comparator<Project>
	{
	    public int compare(Project a, Project b)
	    {
	        return a.finish < b.finish ? -1 : a.finish == b.finish ? 0 : 1;
	    }
	}
	 

