package com.project.Booktion.service;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiResponse {
    @JsonProperty("TOTAL_COUNT")
    private String totalCount;

    @JsonProperty("docs")
    private List<BookDocument> documents;

    @JsonProperty("PAGE_NO")
    private String pageNo;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<BookDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<BookDocument> documents) {
        this.documents = documents;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public static class BookDocument {
        @JsonProperty("PUBLISHER")
        private String publisher;

        @JsonProperty("AUTHOR")
        private String author;

        @JsonProperty("EA_ISBN")
        private String isbn;

        @JsonProperty("PRE_PRICE")
        private String price;

        @JsonProperty("PUBLISH_PREDATE")
        private String publishPredate;

        @JsonProperty("TITLE")
        private String title;


        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPublishPredate() {
            return publishPredate;
        }

        public void setPublishPredate(String publishPredate) {
            this.publishPredate = publishPredate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String titleUrl) {
            this.title = titleUrl;
        }

    }
}
