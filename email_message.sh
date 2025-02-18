#!/usr/bin/env bash

SENDER_ADDRESS="gitlab@example.com"
SUBJECT='GitLab CI'
ATTACHMENT_FILE='target/site/allure-maven-plugin/index.html'
MESSAGE="Project: $CI_PROJECT_NAME
URL: $CI_PROJECT_URL/pipelines/$CI_PIPELINE_ID/
Branch: $CI_COMMIT_REF_SLUG"

curl --ssl-reqd --url "smtps://$SMTP_SERVER:$PORT" \
    --user "$USER:$PASS" \
    --mail-from "$SENDER_ADDRESS" \
    --mail-rcpt "$RECIPIENT_ADDRESS" \
    --header "Subject: $SUBJECT" \
    --header "From: Gitlab <$SENDER_ADDRESS>" \
    --form "=$MESSAGE" \
    --form file=@$ATTACHMENT_FILE
