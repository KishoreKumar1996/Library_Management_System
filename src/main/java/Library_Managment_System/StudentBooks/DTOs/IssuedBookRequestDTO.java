package Library_Managment_System.StudentBooks.DTOs;

public class IssuedBookRequestDTO {
        private int bookId;
        private int cardId;
        //student associated with card, so separate attribute not reqd

        //constructors
        public IssuedBookRequestDTO() {

        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public int getCardId() {
            return cardId;
        }

        public void setCardId(int cardId) {
            this.cardId = cardId;
        }
    }

