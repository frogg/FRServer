#python test server
from flask import Flask, jsonify,request

app = Flask(__name__)

@app.route('/test/post', methods=['POST'])
def test_post():
	name = request.form.get('name',None)
	print(name)
	#default value
	lastName = request.form.get('lastName',"Quappi")
	print(lastName)
	toReturn = {"success": True, "Hi": lastName}
	return jsonify(toReturn);

if __name__ == '__main__':
	app.run(debug=True, host='0.0.0.0')