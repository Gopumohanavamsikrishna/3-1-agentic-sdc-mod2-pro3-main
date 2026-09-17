import os
from flask import Flask, request, jsonify
import google.generativeai as genai
from dotenv import load_dotenv

# Path to the local.properties in the root directory
# (Going up one level from the backend folder)
dotenv_path = os.path.join(os.path.dirname(__file__), '..', 'local.properties')
load_dotenv(dotenv_path=dotenv_path)

app = Flask(__name__)

# Configure Gemini
api_key = os.getenv("GEMINI_API_KEY")
if not api_key:
    print("Error: GEMINI_API_KEY not found in local.properties")
else:
    genai.configure(api_key=api_key)
    model = genai.GenerativeModel('gemini-1.5-flash')

@app.route('/generate', methods=['POST'])
def generate():
    try:
        data = request.json
        # Extract 'prompt' to match your Kotlin PromptRequest class
        user_prompt = data.get('prompt')

        if not user_prompt:
            return jsonify({"reply": "No prompt provided", "status": "error"}), 400

        # Call Gemini
        response = model.generate_content(user_prompt)

        # Return JSON matching your Kotlin PromptResponse class
        return jsonify({
            "reply": response.text,
            "status": "success"
        })
    except Exception as e:
        return jsonify({"reply": str(e), "status": "error"}), 500

if __name__ == '__main__':
    # host='0.0.0.0' allows the Android Emulator to connect to your PC
    app.run(host='0.0.0.0', port=5000, debug=True)