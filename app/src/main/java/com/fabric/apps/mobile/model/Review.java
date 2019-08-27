package com.fabric.apps.mobile.model;

public class Review {

    public String reviewerName;
    public String review;
    public int reviewerImage;
    public String reviewDate;
    public float rating;

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getReviewerImage() {
        return reviewerImage;
    }

    public void setReviewerImage(int reviewerImage) {
        this.reviewerImage = reviewerImage;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Review() {
    }
}
