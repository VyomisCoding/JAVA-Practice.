class FitnessUtil {

    public static double calculateCalories(String routine, double weight) throws InvalidActivityException, InvalidDurationOrIntensityException, InvalidWeightException, InvalidFormatException {

        String[] arr = routine.split(":");
        
        if(arr.length != 3){
            throw new InvalidFormatException("Error:Invalid activity data format.");
        }

        String activity = arr[0];
        int duration;
        int intensity;

        try{
            duration = Integer.parseInt(arr[1]);
            intensity = Integer.parseInt(arr[2]);
        }catch (Exception e){
            throw new InvalidDurationOrIntensityException("Error:Invalid duration or intensity.");
        }

        // Validate weight
        if(weight <= 0){
            throw new InvalidWeightException("Error:User Weight is invalid.");
        }

        // Validate activity
        int caloriesPerMin = 0;
        if(activity.equals("Walking")) caloriesPerMin = 3;
        else if (activity.equals("Running")) caloriesPerMin = 7;
        else if (activity.equals("Cycling")) caloriesPerMin = 5;
        else throw new InvalidActivityException("Error:Invalid activity name.");

        // Validate duration + intensity
        if (duration <= 0 || intensity < 1 || intensity > 9) {
            throw new InvalidDurationOrIntensityException("Error:Invalid duration or intensity.");
        }

        // Apply Formula
        double total = (caloriesPerMin * duration * intensity * 60.0) / weight;

        return total;
    }
}