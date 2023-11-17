import CloudDownloadRoundedIcon from '@mui/icons-material/CloudDownloadRounded';
import Fab from '@mui/material/Fab';

const ButtonFloat = () => {
  
  const handleDownload = () => {
    const downloadLink = document.createElement('a');
    
    downloadLink.href = process.env.PUBLIC_URL + '/output.csv'; // Diretamente da pasta public
    
    downloadLink.download = 'output.csv';
    
    document.body.appendChild(downloadLink);
    
    downloadLink.click();
    
    document.body.removeChild(downloadLink);
  }
  return (
    
    <Fab onClick={handleDownload} sx={{ position: "fixed", top: "20px", right: "20px", padding: "20px", border: "none", borderRadius: "5px", cursor: "pointer" }} variant="extended">
    
      <CloudDownloadRoundedIcon sx={{ mr: 1 }}  />
    
      Download
    
    </Fab>
  )
};

export default ButtonFloat;
