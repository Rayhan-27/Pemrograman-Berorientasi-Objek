class Propagate {
    
    public static void main(String[] args) {
        try {
            System.out.println(reverse("This is a string"));
        }
        catch (Exception e) {
            System.out.println("The String was blank");
        }
        finally {
            System.out.println("All done");
        }
    }

    public static String reverse(String s) throws Exception {
        if (s.length() == 0) { //bila string kosong maka throw new Exception
            throw new Exception();
        }
        String reverseStr = ""; // untuk menyimpan string yang dibalik
        for (int i=s.length()-1 ; i>=0 ; --i) { // untuk membalikkan string dimulai dari akhir
            reverseStr += s.charAt(i); // menambahkan karakter ke reverseStr
        }
        return reverseStr;
    }
}
// hasilnya "gnirts a si sihT" karena s.length() == 0 nya false jadi tidak ada exception yang dilempar
// bila System.out.println(reverse("")) maka akan masuk ke s.length() == 0 dan throw new Exception dan hasilnya "The String was blank"