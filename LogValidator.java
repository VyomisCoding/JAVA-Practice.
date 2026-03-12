public class LogValidator {

    // Method to validate full log
    public static String validateLog(String log) {
        String[] parts = log.split("\\|");
        
        if(parts.length != 5){
            return "INVALID LOG";
        }

        String txnId = parts[0];
        String date = parts[1];
        String amount = parts[2];
        String currency = parts[3];
        String status = parts[4];

        if(!validateTransactionId(txnId)) return "INVALID LOG";

        if(!validateDate(date))  return "INVALID LOG";

        if(!validateAmount(amount)) return "INVALID LOG";

        if(!validateCurrency(currency)) return "INVALID LOG";

        if(!validateStatus(status)) return "INVALID LOG";

        return "VALID LOG";
    }

    // 1) Validate TXN ID => TXN-XXXXXX
    private static boolean validateTransactionId(String txnId) {
        // Your logic
        if(!txnId.matches("TXN-[1-9][0-9]{5}")){
            return false;
        }
        return true;
    }

    // 2) Validate Date YYYY-MM-DD
    private static boolean validateDate(String date) {
        // Your logic
        if(!date.matches("\\d{4}-\\d{2}-\\d{2}"))  return false;

        String[] p =date.split("-");
        int year = Integer.parseInt(p[0]);
        int month = Integer.parseInt(p[1]);
        int day = Integer.parseInt(p[2]);

        if(year < 2009 || year > 2099) return false;

        if(month < 1 || month >12) return false;

        int maxDay;

        switch(month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                maxDay = 31;
                break;

            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;

            case 2:
                if(isLeapYear(year)) maxDay = 29;
                else maxDay = 28;
                break;
            
            default:
                return false;
        }
        return day >= 1 && day <= maxDay;
    }

    // 2a) Optional helper: check leap year
    private static boolean isLeapYear(int year) {
        // Your logic
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    // 3) Validate Amount
    private static boolean validateAmount(String amount) {
        // Your logic
        if(!amount.contains(".")) return false;

        String[] p =amount.split("\\.");
        if(p.length != 2) return false;

        String before = p[0];
        String after = p[1];

        double val;

        try {
            val = Double.parseDouble(amount);
        } catch (Exception e) {
            return false;
        }

        if(val <= 0 || val >= 1000000.0) return false;

        if(before.equals("0")){
            return after.length() >= 1;
        }

        return after.length() == 2;
    }

    // 4) Validate Currency
    private static boolean validateCurrency(String currency) {
        // Your logic
        String[] validCurrencies = {"USD","INR","EUR","GBP"};
        for(String c:validCurrencies){
            if(c.equals(currency)) return true;
        }
        return false;
    }

    // 5) Validate Status
    private static boolean validateStatus(String status) {
        // Your logic
        return status.equals("SUCCESS") ||
               status.equals("FAILED") ||
               status.equals("PENDING");
    }


    // -------------------------
    // MAIN - TESTING PURPOSE
    // -------------------------
    public static void main(String[] args) {
        String[] logs = {
            "TXN-672928|2024-04-31|500.60|USD|SUCCESS",   // INVALID (April has 30 days)
            "TXN-072933|2016-02-28|7683.78|INR|PENDING",  // INVALID (Leading zero in TXN)
            "TXN-762738|2068-10-23|0678.60|USD|FAILED",   // VALID (leading 0 allowed with any decimals)
            "TXN-123456|2024-02-29|250.50|USD|SUCCESS"    // VALID (leap year check)
        };

        for (String log : logs) {
            System.out.println(log + " => " + validateLog(log));
        }
    }
}