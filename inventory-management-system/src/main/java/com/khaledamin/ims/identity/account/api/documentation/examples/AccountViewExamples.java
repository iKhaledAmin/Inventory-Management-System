package com.khaledamin.ims.identity.account.api.documentation.examples;

public final class AccountViewExamples {

    private AccountViewExamples() {
    }

    public static final String SUCCESS_SHORT_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "code": "ACC-01K123ABC456DEF789GHIJKL",
            "username": "khaled-amin",
            "email_address": "khaledamin@example.com",
            "status": "ACTIVE",
            "profile": {
              "first_name": "Khaled",
              "last_name": "Amin",
              "birth_date": "2020-05-20",
              "phone_number": "+201001234567",
              "profession": "Software Engineer",
              "gender": "MALE",
              "image": {}
            }
          }
        }
        """;

    public static final String SUCCESS_FULL_RESPONSE = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "data": {
            "code": "ACC-01K123ABC456DEF789GHIJKL",
            "username": "khaled-amin",
            "email_address": "khaledamin@example.com",
            "status": "ACTIVE",
            "profile": {
              "first_name": "Khaled",
              "last_name": "Amin",
              "birth_date": "2020-05-20",
              "phone_number": "+201001234567",
              "profession": "Software Engineer",
              "gender": "MALE",
              "image": {
                "code": "IMG-01KXYZ123ABC456DEF789GHIJK",
                "variants": [
                  {
                    "resolution": "ORIGINAL",
                    "url": "http://localhost:8080/media/images/profile/IMG-01KXYZ123ABC456DEF789GHIJK/large.webp",
                    "width": 1200,
                    "height": 1200
                  },
                  {
                    "resolution": "LARGE",
                    "url": "http://localhost:8080/media/images/profile/IMG-01KXYZ123ABC456DEF789GHIJK/large.webp",
                    "width": 900,
                    "height": 600
                  },
                  {
                    "resolution": "SQUARE_MEDIUM",
                    "url": "http://localhost:8080/media/images/profile/IMG-01KXYZ123ABC456DEF789GHIJK/square-medium.webp",
                    "width": 600,
                    "height": 600
                  }
                ]
              }
            }
          }
        }
        """;

    public static final String ACCOUNT_NOT_FOUND = """
        {
          "meta": {
            "timestamp": "2026-06-29T15:30:00",
            "request_id": "01K123ABC456DEF789GHIJKL"
          },
          "error": {
            "status": 404,
            "code": "ACCOUNT_NOT_FOUND",
            "message": "Account not found",
            "path": "/account",
            "details": {}
          }
        }
        """;

}