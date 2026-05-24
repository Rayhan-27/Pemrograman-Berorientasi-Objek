class RangeErrorException extends Throwable { // membuat jenis error baru bernama RangeErrorException yang merupakan turunan dari Throwable
    public RangeErrorException(String s) {
        super(s);
    }

    public static void main(String[] args) {
        int position = 1;
        try {
            if (position > 0){ // karena position = 1 maka masuk ke kondisi if
                throw new RangeErrorException("Position " + position); // program sengaja melempar error dengan pesan "Position 1"
            }
        }
        catch (RangeErrorException e) { // error di tangkap di RangeErrorException
            System.out.println("Range error: " + e.getMessage()); // menampilkan pesan error
        }
        System.out.println("This is the last program.");
    }
}
