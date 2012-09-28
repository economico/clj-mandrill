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

## Configuring API Keys

The easiest way to set it up is to set your api key in the MANDRILL_API_KEY environment variable or system property.

Alternatively you can create a dynamic binding for *mandrill-api-key*.


## Use Mailchimp?

We also maintain a simple clojure library [Climp](https://github.com/economico/climp) for interacting with Mailchimp.

## License

Copyright © 2012 PicoMoney Company

Manage and track your startups economy using our new service [Economi.co](http://economi.co).

Distributed under the Eclipse Public License, the same as Clojure.
