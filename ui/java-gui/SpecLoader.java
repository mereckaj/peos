/*
 * SpecLoader.java
 *  Contains all the relevant functions and loaders for peos SPEC files.
 *
 */
import java.io.*;
/**
 *
 * @author Steve Beeby
 */
public class SpecLoader {
    
    /**
     * This method converts a PML pathname to its corresponding spec file.
     * @param path The pathname (string).
     * @return The pathname of the corresponding spec file.
     */
    public static String getSpecFileName(String path)
    {
        if ( (path.equals("")) || (path.length() < 5) )
            return "Not a valid PML file.";
        if (path.substring(path.length() -4).equals(".pml") == false)       
            return "Not a valid PML file.";
        String specPath = path.substring(0,path.length()-4);
        specPath=specPath.concat(".res"); 
        return specPath;
    }
    
    /**
    * This method loads the spec file from a given specfile path.
    * It then binds the resources using the outline given for a specific
    * pid.
    *
    * @param path The path of the spec file. (.res)
    * @param dpo The outline.
    * @param pidNum the number of the desired process. 
    * @return An error code. Returns 1 if file does not exist or is not readable.
     *        Returns 2 if an exception is thrown. Returns 3 if a line does not 
     *        follow the appropriate format. Returns 0 on successful completion. 
    */
    public static int LoadSpecFile(String path, displayPO dpo, int pidNum)      
    {
        String line;        
        File f1 = new File(path);
        if (f1.canRead() == false)        
            return 1;
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null)
            {            
                String[] pair=line.split(":");                
                if (pair.length > 2)                
                    return 3;
                dpo.bindResource(pair[0],pair[1],pidNum);
            }
            return 0;            
        }
        catch(IOException oops)
        {
            System.err.println(oops);
            return 2;
        }                      
    }
}
