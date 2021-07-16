<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
            />
    </head>
    <style>
        .row {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            margin-right: -15px;
            margin-left: -15px;
            flex-direction: row;
            align-content: center;
            justify-content: center;
        }
    </style>
    <body>
        <div style="background: #f5f5f5; padding-bottom: 100px; padding-top: 100px">
            <div class="row bootstrap snippets bootdeys">
                <div class="col-md-5 col-md-offset-3">
                    <div class="text-center logo-alt-box">
                        <a
                            href="index.html"
                            class="logo"
                            style="
                            float: none;
                            color: #3ec845;
                            font-size: 24px;
                            letter-spacing: 0.06em;
                            line-height: 46px;
                            "
                            >
                            <span>OnlineLearning.com</span>
                        </a>
                        <h5 class="text-muted m-t-0" style="font-size: 12px">
                            Your account has been created!!
                        </h5>
                    </div>
                    <div
                        class="m-t-30 card-box"
                        style="
                        margin-bottom: 20px;
                        background-clip: padding-box;
                        -moz-border-radius: 5px;
                        border-radius: 5px;
                        -webkit-border-radius: 5px;
                        padding: 20px;
                        background-color: #ffffff;
                        box-shadow: 0 8px 42px 0 rgba(0, 0, 0, 0.08);
                        "
                        >
                        <div class="text-center">
                            <h4 class="text-uppercase font-bold m-b-0">Confirmation !!</h4>
                        </div>
                        <div class="code-box">
                            <form action="ValidateNewAccount" >
                                <div class="form-group">
                                    <label for="email-code">Email</label>
                                    <input type="text" class="form-control" name="txtEmail" id="email-code" placeholder="enter your email"/>
                                    <label for="confirmation-code">Verification code</label>
                                    <input type="text" class="form-control" name="txtValidationNums" id="confirmation-code" placeholder="enter your code"/>
                                    <button type="submit" value="Verify" class="btn btn-primary mt-4">Verify</button>
                                </div>
                            </form>
                        </div>
                        <div class="panel-body text-center">
                            <p class="text-muted font-13 m-t-20" style="color: #9a9da0">
                                A verification code has been sent to <b>youremail@domain.com</b>. To
                                activate your account, please enter verification code.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
