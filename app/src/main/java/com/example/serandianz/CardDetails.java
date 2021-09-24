package com.example.serandianz;

public class CardDetails {
    private Integer CardNo;
    private Integer ExpYear;
    private Integer ExpMonth;
    private Integer Cvv;

    public CardDetails() {}

    public CardDetails(Integer cardNo, Integer expYear, Integer expMonth, Integer cvv) {
        CardNo = cardNo;
        ExpYear = expYear;
        ExpMonth = expMonth;
        Cvv = cvv;
    }

    public Integer getCardNo() {
        return CardNo;
    }

    public void setCardNo(Integer cardNo) {
        CardNo = cardNo;
    }

    public Integer getExpYear() {
        return ExpYear;
    }

    public void setExpYear(Integer expYear) {
        ExpYear = expYear;
    }

    public Integer getExpMonth() {
        return ExpMonth;
    }

    public void setExpMonth(Integer expMonth) {
        ExpMonth = expMonth;
    }

    public Integer getCvv() {
        return Cvv;
    }

    public void setCvv(Integer cvv) {
        Cvv = cvv;
    }
}
