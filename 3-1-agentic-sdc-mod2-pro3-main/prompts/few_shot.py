def build_prompt(task):

    return f"""

Example

Question:
What is Artificial Intelligence?

Answer:
Artificial Intelligence is the simulation of human intelligence by computers.

Now answer:

{task}

"""