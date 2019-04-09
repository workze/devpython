'''
The MIT License (MIT)
Copyright (c) 2013 Dave P.
'''

from SimpleWebSocketServer import WebSocket, SimpleWebSocketServer


class SimpleEcho(WebSocket):

    def handleMessage(self):
        self.sendMessage(self.data)

    def handleConnected(self):
        pass

    def handleClose(self):
        pass


clients = []
client_map = {}  # "/kafka/123342"


class SimpleChat(WebSocket):

    def handleMessage(self):
        print (self.address, cid(self), ':', self.data)
        for client in clients:
            if client != self:
                client.sendMessage(self.address[0] + u' - ' + self.data)

    def handleConnected(self):
        print (self.address, cid(self), 'connected')
        # for client in clients:
        #    client.sendMessage(self.address[0] + u' - connected')
        self.sendMessage(self.address[0] + u' - connected')
        clients.append(self)
        client_map[cid(self)] = self

    def handleClose(self):
        clients.remove(self)
        client_map.pop(cid(self))
        print (self.address, cid(self), 'closed')
        # for client in clients:
        #   client.sendMessage(self.address[0] + u' - disconnected')


def cid(client):
    return client.request.path


def send_message_to_client(clientid, message):
    print 'send_message_to_client', clientid, message
    if clientid not in client_map:
        print('send_message_to_client err, client not exists.')
    client = client_map.get(clientid)
    client.sendMessage(message)


def start_wsserver():
    print('start_wsserver on (0.0.0.0:8000)')
    clss = SimpleChat
    wsserver = SimpleWebSocketServer('0.0.0.0', 8000, clss)
    wsserver.serveforever()


if __name__ == "__main__":
    cls = SimpleChat
    server = SimpleWebSocketServer('0.0.0.0', 8000, cls)
    server.serveforever()
