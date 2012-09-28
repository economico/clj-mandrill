# clj-mandrill

A simple Clojure library wrapper for [Mandrill](https://mandrillapp.com) the transactional email service from MailChimp.

[![Build Status](https://secure.travis-ci.org/economico/clj-mandrill.png)](http://travis-ci.org/economico/clj-mandrill)

## Usage

Add the following to your project.clj's dependencies section:

```clojure
[clj-mandrill "0.1.0"]
```

Import the library:

```clojure
(use 'clj-mandrill.core)
```

You can call any method listed in the [Mandrill V1.0 API](https://mandrillapp.com/api/docs/)

```clojure
(call-mandrill "users/info" {})
```

A few of the methods have nicer clojure wrappers around them. I hope to expand this as we go. I will accept pull requests.

### Send message

Send an arbitrary message. See [Send message API doc](https://mandrillapp.com/api/docs/messages.html#method=send) for contents of message map:

```clojure
(send-message {:text "Hi" :subject "Just a note" :from_email "alice@test.com" :from_name "Alice"
                :to [{:email "bob@test.com" :name "Bob"})
```

### Send message through a template

Mandril supports using templates. This allows you to manage your email content and design outside your app.

Go to [My Templates](https://mandrillapp.com/templates/my-templates) or create them in MailChimp.

See [Send message template API doc](https://mandrillapp.com/api/docs/messages.html#method=send-template) for contents of message map:

```clojure
(send-template "verify_email"
                {:subject "Just a note" :from_email "alice@test.com" :from_name "Alice"
                  :to [{:email "bob@test.com" :name "Bob"})
```

### User information APIS

These are helpful for testing out your connection and validating your emails.

See [Users API](https://mandrillapp.com/api/docs/users.html)

```clojure
=> (ping) ;; Ping server and validate keys
"PONG"

=> (user-info)
{:username "YOURUSERNAME", :created_at "2012-06-29 17:47:55", :public_id "...", :reputation 50, :hourly_quota 25, :backlog 0, :stats {:today {:sent 5, :hard_bounces 0, :complaints 0, :unique_opens 1, :rejects 0, :clicks 1, :soft_bounces 0, :opens 1, :unsubs 0, :unique_clicks 1}, :last_7_days {:sent 5, :hard_bounces 0, :complaints 0, :unique_opens 1, :rejects 0, :clicks 1, :soft_bounces 0, :opens 1, :unsubs 0, :unique_clicks 1}, :last_30_days {:sent 5, :hard_bounces 0, :complaints 0, :unique_opens 1, :rejects 0, :clicks 1, :soft_bounces 0, :opens 1, :unsubs 0, :unique_clicks 1}, :last_60_days {:sent 5, :hard_bounces 0, :complaints 0, :unique_opens 1, :rejects 0, :clicks 1, :soft_bounces 0, :opens 1, :unsubs 0, :unique_clicks 1}, :last_90_days {:sent 5, :hard_bounces 0, :complaints 0, :unique_opens 1, :rejects 0, :clicks 1, :soft_bounces 0, :opens 1, :unsubs 0, :unique_clicks 1}, :all_time {:sent 5, :hard_bounces 0, :complaints 0, :unique_opens 1, :rejects 0, :clicks 1, :soft_bounces 0, :opens 1, :unsubs 0, :unique_clicks 1}}}

=> (senders)
[{:sent 5, :hard_bounces 0, :complaints 0, :unique_opens 1, :rejects 0, :clicks 1, :soft_bounces 0, :created_at "2012-09-28 14:57:41", :opens 1, :unsubs 0, :address "alice@test.com", :unique_clicks 1}]

```


## Configuring API Keys

The easiest way to set it up is to set your api key in the MANDRILL_API_KEY environment variable or system property.

Alternatively you can create a dynamic binding for *mandrill-api-key*.


## Use Mailchimp?

We also maintain a simple clojure library [Climp](https://github.com/economico/climp) for interacting with Mailchimp.

## License

Copyright © 2012 PicoMoney Company

Manage and track your startups economy using our new service [Economi.co](http://economi.co).

Distributed under the Eclipse Public License, the same as Clojure.
