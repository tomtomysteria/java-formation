export const handler = async (event) => {
  const now = new Date().toLocaleString('fr-FR');
  return {
    statusCode: 200,
    body: JSON.stringify({ message: `Bonjour ! Il est ${now}` }),
  };
};
