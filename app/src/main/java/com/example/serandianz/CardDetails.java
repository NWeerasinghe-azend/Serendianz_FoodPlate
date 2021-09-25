package com.example.serandianz;

public class CardDetails {
    private Integer  cardNo;
    private Integer  expYear;
    private Integer  expMonth;
    private Integer  CVC;
    private String cardName;

    public CardDetails() {       //create empty constructors
    }

    public Integer getCardNo() {     //Generate getters and setters
        return cardNo;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getCVC() {
        return CVC;
    }

    public void setCVC(Integer CVC) {
        this.CVC = CVC;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
