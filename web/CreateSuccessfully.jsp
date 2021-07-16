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
        body {
            margin-top: 20px;
        }

        .card-box .card-drop {
            color: #9a9da0;
            font-size: 20px;
            line-height: 1px;
            padding: 0px 5px;
            display: inline-block;
        }
        .text-success {
        }
        .text-muted {
            color: #9a9da0 !important;
        }
        .logo-alt-box .logo {
        }
        .logo-alt-box h5 {
        }
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
        .checkmark__circle {
            stroke-dasharray: 166;
            stroke-dashoffset: 166;
            stroke-width: 2;
            stroke-miterlimit: 10;
            stroke: #7ac142;
            fill: none;
            animation: stroke 0.6s cubic-bezier(0.65, 0, 0.45, 1) forwards;
        }

        .checkmark {
            width: 56px;
            height: 56px;
            border-radius: 50%;
            display: block;
            stroke-width: 2;
            stroke: #fff;
            stroke-miterlimit: 10;
            margin: 10% auto;
            box-shadow: inset 0px 0px 0px #7ac142;
            animation: fill 0.4s ease-in-out 0.4s forwards,
                scale 0.3s ease-in-out 0.9s both;
        }

        .checkmark__check {
            transform-origin: 50% 50%;
            stroke-dasharray: 48;
            stroke-dashoffset: 48;
            animation: stroke 0.3s cubic-bezier(0.65, 0, 0.45, 1) 0.8s forwards;
        }

        @keyframes stroke {
            100% {
                stroke-dashoffset: 0;
            }
        }
        @keyframes scale {
            0%,
            100% {
                transform: none;
            }
            50% {
                transform: scale3d(1.1, 1.1, 1);
            }
        }
        @keyframes fill {
            100% {
                box-shadow: inset 0px 0px 0px 30px #7ac142;
            }
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
                        <h2 class="text-muted m-t-0" style="font-size: 24px">
                            Your account has been activated!!
                        </h2>
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
                            <h4 class="text-uppercase font-bold m-b-0">Success !!</h4>
                        </div>
                        <div class="panel-body text-center">
                            <svg
                                class="checkmark"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 52 52"
                                >
                            <circle
                                class="checkmark__circle"
                                cx="26"
                                cy="26"
                                r="25"
                                fill="none"
                                />
                            <path
                                class="checkmark__check"
                                fill="none"
                                d="M14.1 27.2l7.1 7.2 16.7-16.8"
                                />
                            </svg>
                            <p class="text-muted font-13 m-t-20" style="color: #9a9da0">
                            <div>You will be redirect to the page in <span id="time">10</span> seconds!</div>
                            or click this link (<a href="HomePage">OnlineLearning.com</a>)to return to home page.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/js/timecounter.js"></script>
    </body>
</html>
