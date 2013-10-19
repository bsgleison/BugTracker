(function () {
    var $, VideoChat;

    $ = jQuery;

    $.fn.extend({
        videoChatInit: function (options) {
            var settings,
                _this = this;
            settings = {
                apiKey: null,
                sessionId: null,
                token: null
            };
            settings = $.extend(settings, options);
            if (!(this.length > 0)) {
                console.error("Error, could not find any elements to work with selector " + this.selector, this);
            }
            return this.each(function (idx, element) {
                return new VideoChat(settings.apiKey, settings.sessionId, settings.token, $(element));
            });
        }
    });

    VideoChat = (function () {

        function VideoChat(apiKey, sessionId, token, mainDiv) {
            var _this = this;
            this.apiKey = apiKey;
            this.sessionId = sessionId;
            this.token = token;
            this.mainDiv = mainDiv;
            this.subscribers = {};
            TB.setLogLevel(TB.DEBUG);
            TB.addEventListener("exception", this.exceptionHandler);
            if (TB.checkSystemRequirements() !== TB.HAS_REQUIREMENTS) {
                alert("You don't have the minimum requirements to run this application.");
            } else {
                console.log("the main div", this.mainDiv);
            }
            this.session = TB.initSession(this.sessionId);
            this.session.addEventListener('sessionConnected', function (event) {
                var stream, _i, _len, _ref, _results;
                console.log("Got a sessionConnectedHandler event!");
                _this.startPublishing();
                _ref = event.streams;
                _results = [];
                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                    stream = _ref[_i];
                    _results.push(_this.addStream(stream));
                }
                return _results;
            });
            this.session.addEventListener('sessionDisconnected', function (event) {
                return _this.publisher = null;
            });
            this.session.addEventListener('connectionCreated', function (event) {
            });
            this.session.addEventListener('connectionDestroyed', function (event) {
            });
            this.session.addEventListener('streamCreated', function (event) {
                var stream, _i, _len, _ref, _results;
                _ref = event.streams;
                _results = [];
                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                    stream = _ref[_i];
                    _results.push(_this.addStream(stream));
                }
                return _results;
            });
            this.session.addEventListener('streamDestroyed', function (event) {
                var stream, _i, _len, _ref, _results;
                console.log("Got stream destroyed, ", event);
                _ref = event.streams;
                _results = [];
                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                    stream = _ref[_i];
                    _results.push(_this.removeStream(stream));
                }
                return _results;
            });
            this.session.connect(this.apiKey, this.token);
        }

        VideoChat.prototype.createDivForVideo = function (title, id) {
            var videoDiv;
            console.log("Creating div for title '", title, "' and id '", id, "'");
            videoDiv = $('<div id="parent_' + id + '"/>').addClass("videoContainer");
            videoDiv.append($('<div id="' + id + '"/>').addClass("videoTokBoxItem"));
            videoDiv.append($('<h5>' + title + '</h5>'));
            this.mainDiv.append(videoDiv);
            return videoDiv;
        };

        VideoChat.prototype.addStream = function (stream) {
            var subscriberDiv;
            if (stream.connection.connectionId === this.session.connection.connectionId) {
                console.log("My own stream in addStream, returning.");
                return;
            }
            console.log("adding stream, ", stream);
            subscriberDiv = this.createDivForVideo(stream.connection.data, stream.streamId);
            this.subscribers[stream.streamId] = this.session.subscribe(stream, stream.streamId);
            return true;
        };

        VideoChat.prototype.removeStream = function (stream) {
            var parentDiv, sId, videoDiv;
            sId = stream.streamId;
            videoDiv = $('#' + sId);
            parentDiv = $('#parent_' + sId);
            console.log("removing sId", sId, " div is ", videoDiv, " parent is ", parentDiv);
            return parentDiv.remove();
        };

        VideoChat.prototype.startPublishing = function () {
            var publisherDiv;
            if (!(this.publisher != null)) {
                publisherDiv = this.createDivForVideo("You", "opentok_publisher");
                return this.publisher = this.session.publish("opentok_publisher");
            }
        };

        VideoChat.prototype.exceptionHandler = function (event) {
            return console.error("Exception caught by exceptionhandler: ", event.code, event.message, event);
        };

        return VideoChat;

    })();

}).call(this);